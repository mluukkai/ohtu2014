package com.mycompany.webkauppa.scenarios;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromRelativeFile;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.reporters.StoryReporterBuilder.Format;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.Test;

public class ScenariosBase extends JUnitStory {
    private Class stepDefinitionClass;
    
    public ScenariosBase(Class stepDefinitionClass) {
        super(); 
        this.stepDefinitionClass = stepDefinitionClass;   
    }   
    
    @Override
    public Configuration configuration() {
        URL storyURL = null;
        try {
            // This requires you to start Maven from the project directory
            storyURL = new URL("file://" + System.getProperty("user.dir")
                    + "/src/main/resources/stories/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new MostUsefulConfiguration().useStoryLoader(
                new LoadFromRelativeFile(storyURL)).useStoryReporterBuilder(
                new StoryReporterBuilder().withFormats(Format.HTML));
    }

    @Override
    public List<CandidateSteps> candidateSteps() {    
        Object stepDefiningObject = null;
        try {
            stepDefiningObject = stepDefinitionClass.newInstance();
        
        } catch(Exception e){}              
        
        return new InstanceStepsFactory(configuration(), stepDefiningObject).createCandidateSteps();   
    }

    @Override
    @Test
    public void run() {
        try {
            super.run();
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
