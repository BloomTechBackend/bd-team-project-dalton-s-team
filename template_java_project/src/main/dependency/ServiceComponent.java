package main.dependency;

import dagger.Component;
import main.activity.CreateBudgetActivity;

import javax.inject.Singleton;

@Singleton
@Component(modules = { DaoModule.class })
public interface ServiceComponent {
    CreateBudgetActivity provideCreateBudgetActivity();

}
