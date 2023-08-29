package main.dependency;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.Module;
import dagger.Provides;
import main.dynamodb.BudgetDao;
import main.dynamodb.SpendingCategoryDao;

import javax.inject.Singleton;

@Module
public class DaoModule {
    @Singleton
    @Provides
    public BudgetDao provideBudgetDao() {
        return new BudgetDao(provideDynamoDBMapper());
    }

    @Singleton
    @Provides
    public DynamoDBMapper provideDynamoDBMapper() {
        AmazonDynamoDB amazonDynamoDBClient = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .withRegion(Regions.US_WEST_2)
                .build();

        return new DynamoDBMapper(amazonDynamoDBClient);
    }

    @Singleton
    @Provides
    public SpendingCategoryDao provideSpendingCategoryDao() {
        return new SpendingCategoryDao(provideDynamoDBMapper());
    }
}