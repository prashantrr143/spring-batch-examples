package com.batch.springdemobatch.multisteps.config;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.batch.springdemobatch.batch.extensions.TransactionItemReadListener;
import com.batch.springdemobatch.batch.extensions.TransactionItemSkipListerner;
import com.batch.springdemobatch.batch.extensions.TransactionRowMapper;
import com.batch.springdemobatch.batch.extensions.TransactionValidator;
import com.batch.springdemobatch.model.Transaction;

/**
 * Configuration for Spring batch supporting multiple steps
 * 
 * We are not configuring a separate JobRepository here. Using the default
 * configured by @EnableBatchProcessing
 * 
 * @author prashantsingh
 *
 */

@Configuration
@Profile({ "steps", "csv-xml" })
public class MultiStepsBatchConfiguration {

	/**
	 * Declaring a Configuration Scope
	 * 
	 * This scope has beans defined for DB to XML Configuration Step
	 * 
	 * @author prashantsingh
	 *
	 */

	@Configuration
	@Profile({ "steps", "csv-xml" })
	public static class DBtoXMLBatchStepConfiguration {

		@Value("${spring-batch.single.output.location.pathxml}")
		private FileSystemResource outputResource;

		@Autowired
		private CSVToXMLBatchJobMetaDataConfigProperties batchMetaData;

		@Bean
		public Marshaller marshaller() {
			Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
			marshaller.setClassesToBeBound(new Class[] { Transaction.class });
			return marshaller;
		}

		// Adding an XML Writer
		@Bean
		ItemWriter<Transaction> xmlItemWriter(TransactionXMLMetadataConfiguration tConfiguration) {
			return new StaxEventItemWriterBuilder<Transaction>().name(batchMetaData.getXmlWriterStepName())
					.resource(outputResource).marshaller(marshaller()).rootTagName(tConfiguration.getRootTagName())
					.build();

		}

		@Bean
		ItemProcessor<Transaction, Transaction> itemProcessor() {
			return new ValidatingItemProcessor<Transaction>(new TransactionValidator());
		}

		@Bean
		ItemReader<Transaction> jdbcItemReader(DataSource dataSource) {
			return new JdbcCursorItemReaderBuilder<Transaction>().sql(Transaction.SELECT_TRANSACTION_SQL)
					.name(batchMetaData.getJdbcReaderStepName()).dataSource(dataSource)
					.rowMapper(new TransactionRowMapper()).build();
		}

	}

	/**
	 * Defining Configuration for CSV to Batch Step Batch Job
	 * 
	 * @author prasingh26
	 *
	 */

	@Configuration
	@Profile({ "steps", "csv-xml" })
	public static class CSVtoDBBatchStepConfiguration {

		/**
		 * Defining an input resource. From this location, batch will try to load the
		 * CSV. Configure path for the CSV file in application.properties.
		 * 
		 */

		@Value("${spring-batch.single.input.resource.path}")
		private FileSystemResource inputCsv;

		/**
		 * Registering a FlatFileItemReaderBuiler as ItemReader.
		 * 
		 * @return
		 */

		@Bean
		ItemReader<Transaction> csvItemReader() {
			return new FlatFileItemReaderBuilder<Transaction>().name("csv-file-item-reader").resource(inputCsv)
					.strict(true).targetType(Transaction.class).delimited().delimiter(",")
					.names(Transaction.FIELDS_METADATA).build();
		}

		@Bean
		ItemProcessor<Transaction, Transaction> itemProcessor() {
			return new ValidatingItemProcessor<Transaction>(new TransactionValidator());
		}

		/**
		 * Registering a JdbcBatchItemWriter to write the read items into data source
		 * configured
		 * 
		 * @param dataSource
		 * @return
		 */
		@Bean
		ItemWriter<Transaction> jdbcItemWriter(DataSource dataSource) {
			return new JdbcBatchItemWriterBuilder<Transaction>().dataSource(dataSource)
					.sql(Transaction.INSERT_TRANSACTION_SQL).beanMapped().build();

		}
	}

	/**
	 * A Spring Configuration : Used for Scoping purpose
	 * 
	 * @author prasingh26
	 *
	 */
	@Configuration
	@Profile("steps")
	public static class DBtoCSVBatchConfiguration {

		@Value("${spring-batch.single.output.resource.path}")
		private FileSystemResource csvOutput;

		@Bean
		ItemReader<Map<Integer, String>> jdbcItemReader(DataSource dataSource) {
			return new JdbcCursorItemReaderBuilder<Map<Integer, String>>()
					.sql(Transaction.SELECT_TRANSACTION_GROUPED_BY_SQL).name("jdbc-raeader").dataSource(dataSource)
					.rowMapper(new RowMapper<Map<Integer, String>>() {
						@Override
						public Map<Integer, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
							return Collections.singletonMap(rs.getInt(1), rs.getString(2));
						}
					}).build();
		}

		@Bean
		ItemWriter<Map<Integer, String>> csvItemWriter() {
			return new FlatFileItemWriterBuilder<Map<Integer, String>>().name("csv-to-db-writer").resource(csvOutput)
					.lineAggregator(new DelimitedLineAggregator<Map<Integer, String>>() {
						{
							setDelimiter(",");
							setFieldExtractor(new FieldExtractor<Map<Integer, String>>() {
								public Object[] extract(Map<Integer, String> entry) {
									Map.Entry<Integer, String> mapEntry = entry.entrySet().iterator().next();
									return new Object[] { mapEntry.getKey(), mapEntry.getValue() };
								}
							});
						}
					}).build();
		}

	}

	/**
	 * Registering a Job( Actual runnable job) in to the context. If Spring finds
	 * any job registered, is starts them.
	 * 
	 * @param dataSource                 Data source configured by Spring Boot
	 *                                   Default Configuration
	 * @param stepBuilderFactory         StepBuilderFactory : used to build Steps
	 *                                   for Job
	 * @param jobBuilderFactory          JobBuilderFactory : used to build Jobs
	 * @param csvToDbConfiguration       A Configuration Object
	 * @param dbBtoCSVBatchConfiguration A Configuration Object
	 * @return Job instance
	 */
	@Bean
	@Profile("steps")
	Job job(DataSource dataSource, StepBuilderFactory stepBuilderFactory, JobBuilderFactory jobBuilderFactory,
			CSVtoDBBatchStepConfiguration csvToDbConfiguration, DBtoCSVBatchConfiguration dbBtoCSVBatchConfiguration,
			CSVToXMLBatchJobMetaDataConfigProperties batchJobMetaData) {

		System.out.println("Started CSV TO CSV batch Job");

		Step csvToDbStep = stepBuilderFactory.get(batchJobMetaData.getCsvReaderStepName())
				.<Transaction, Transaction>chunk(batchJobMetaData.getCsvReadChunkSize())
				.reader(csvToDbConfiguration.csvItemReader()).processor(csvToDbConfiguration.itemProcessor())
				.writer(csvToDbConfiguration.jdbcItemWriter(dataSource)).listener(new TransactionItemReadListener())
				.faultTolerant().skip(FlatFileParseException.class).listener(new TransactionItemSkipListerner())
				.noSkip(FileNotFoundException.class).skipLimit(batchJobMetaData.getCsvReadSkipLimit()).build();

		Step dbToCSVStep = stepBuilderFactory.get("db-to-csv")
				.<Map<Integer, String>, Map<Integer, String>>chunk(batchJobMetaData.getJdbcReadChunkSize())
				.reader(dbBtoCSVBatchConfiguration.jdbcItemReader(dataSource))
				.writer(dbBtoCSVBatchConfiguration.csvItemWriter()).build();

		return jobBuilderFactory.get(batchJobMetaData.getBatchName()).incrementer(new RunIdIncrementer())
				.start(csvToDbStep).next(dbToCSVStep).build();

	}

	@Bean
	@Profile("csv-xml")
	Job csvXmlJob(DataSource dataSource, StepBuilderFactory stepBuilderFactory, JobBuilderFactory jobBuilderFactory,
			CSVtoDBBatchStepConfiguration csvToDbConfiguration, DBtoXMLBatchStepConfiguration dBtoXMLBatchStepConfiguration,
			CSVToXMLBatchJobMetaDataConfigProperties batchJobMetaData) {

		System.out.println("Started CSV TO XML batch Job");
		Step csvToDbStep = stepBuilderFactory.get(batchJobMetaData.getCsvReaderStepName())
				.<Transaction, Transaction>chunk(batchJobMetaData.getCsvReadChunkSize())
				.reader(csvToDbConfiguration.csvItemReader()).processor(csvToDbConfiguration.itemProcessor())
				.writer(csvToDbConfiguration.jdbcItemWriter(dataSource)).listener(new TransactionItemReadListener())
				.faultTolerant().skip(FlatFileParseException.class).listener(new TransactionItemSkipListerner())
				.noSkip(FileNotFoundException.class).skipLimit(batchJobMetaData.getCsvReadSkipLimit()).build();

		Step dbToXMLStep = stepBuilderFactory.get(batchJobMetaData.getJdbcReaderStepName())
				.<Transaction,Transaction>chunk(batchJobMetaData.getJdbcReadChunkSize())
				.reader(dBtoXMLBatchStepConfiguration.jdbcItemReader(dataSource))
				.writer(dBtoXMLBatchStepConfiguration.xmlItemWriter(null)).build();

		return jobBuilderFactory.get(batchJobMetaData.getBatchName()).incrementer(new RunIdIncrementer())
				.start(csvToDbStep).next(dbToXMLStep).build();

	}

}
