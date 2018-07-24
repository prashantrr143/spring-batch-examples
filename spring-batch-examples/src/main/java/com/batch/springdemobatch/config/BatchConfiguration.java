package com.batch.springdemobatch.config;

import java.io.FileNotFoundException;

import javax.batch.api.chunk.listener.ItemReadListener;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.batch.springdemobatch.model.Transaction;


/**
 * Configuration for Spring batch 
 * 
 * @author prashantsingh
 *
 */

@Configuration
public class BatchConfiguration {

	/**
	 * Defining an input resource. From this location, batch will try to load the CSV.
	 * Configure path for the CSV file in application.properties.
	 * 
	 */
	
	@Value("${spring-batch.single.input.resource.path}")
	private FileSystemResource inputCsv;
	
	
	/**
	 * Registering a SkipListener to log the exceptions during skipping
	 * @return
	 */
	@Bean
	SkipListener<Transaction ,Transaction> skipListener(){
		return new SkipListener<Transaction, Transaction>() {

			@Override
			public void onSkipInRead(Throwable t) {
				System.out.println("onSkipInRead  : "+ t.getMessage());
				
			}

			@Override
			public void onSkipInWrite(Transaction item, Throwable t) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSkipInProcess(Transaction item, Throwable t) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	

	/**
	 * Registering an ItemReadListener to listen to Item Read Events
	 * @return
	 */
	
	@Bean
	ItemReadListener itemReadListener() {
		return new ItemReadListener() {

			@Override
			public void onReadError(Exception ex) throws Exception {
				System.out.println("Read Exception Detected  " + ex.getMessage());

			}

			@Override
			public void beforeRead() throws Exception {
				System.out.println("Before Read Invoked");

			}

			@Override
			public void afterRead(Object item) throws Exception {
				// TODO Auto-generated method stub

			}
		};
	}
	
	/**
	 * Registering a FlatFileItemRaederBuiler as ItemReader.
	 * @return
	 */

	@Bean
	ItemReader<Transaction> itemReader() {
		return new FlatFileItemReaderBuilder<Transaction>().name("csv-file-item-reader").resource(inputCsv).strict(true)
				.targetType(Transaction.class).linesToSkip(1).delimited().delimiter(",")
				.names(Transaction.FIELDS_METADATA).build();
	}

	@Bean
	Validator<Transaction> validator() {
		return new Validator<Transaction>() {
			@Override
			public void validate(Transaction value) throws ValidationException {
				System.out.println("Current Transaction Object: " + value);
				if (value.getContactLastName() == null) {
					throw new ValidationException("Rejected Object");
				}
			}
		};
	}

	@Bean
	ItemProcessor<Transaction, Transaction> itemProcessor(Validator<Transaction> validator) {
		return new ValidatingItemProcessor<Transaction>(validator);
	}

	/**
	 * Registering a JdbcBatchItemWriter to write the read items into data source configured
	 * @param dataSource
	 * @return
	 */
	@Bean
	ItemWriter<Transaction> itemWriter(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Transaction>().dataSource(dataSource).sql(Transaction.INSERT_TRANSACTION_SQL)
				.beanMapped().build();

	}

	/**
	 * Registering a Job( Actual runnable job) in to the context.
	 * If Spring finds any job registered, is starts them.
	 * 
	 * @param stepBuilderFactory StepBuilderFactory : used to build Steps for Job
	 * @param jobBuilderFactory JobBuilderFactory : used to build Jobs
	 * @param itemReader : ItemReader : item reader to read the csv files lines and convert them to POJO
	 * @param itemWriter : ItemWriter : item writer to write the chunks into dataasource configured
	 * @param processor  : ItemProcessor: Logic to decided ,if an Item is considered valid or not
	 * @return : A fully configured Job instance
	 */
	
	@Bean
	Job job(StepBuilderFactory stepBuilderFactory, JobBuilderFactory jobBuilderFactory,
			ItemReader<? extends Transaction> itemReader, ItemWriter<? super Transaction> itemWriter,
			ItemProcessor<Transaction, Transaction> processor) {

		Step step = stepBuilderFactory.get("spring-batch-step").<Transaction, Transaction>chunk(100).reader(itemReader)
				.processor(processor).writer(itemWriter).listener(itemReadListener()).faultTolerant()
				/*.skip(FlatFileParseException.class)*/
				.listener(skipListener())

				.noSkip(FileNotFoundException.class).skipLimit(200).build();

		return jobBuilderFactory.get("spring-batch-job1").start(step).build();

	}

}