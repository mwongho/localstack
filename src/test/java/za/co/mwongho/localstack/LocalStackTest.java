package za.co.mwongho.localstack;

import com.amazonaws.regions.Regions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import za.co.mwongho.aws.s3.service.impl.AmazonS3ClientServiceImpl;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@SpringBootTest(
        classes = {
                LocalStackConfig.class, AwsConfigTest.class, LocalstackApplication.class, AmazonS3ClientServiceImpl.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {LocalStackTest.LocalStackInitializer.class})
public abstract class LocalStackTest {

        private final static String region = Regions.US_EAST_1.getName();

        static class LocalStackInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
                @Override
                public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
                        TestPropertyValues.of(
                                "cloud.region.static=" + region,
                                "cloud.aws.s3.bucket=testbucket"
                        ).applyTo(configurableApplicationContext.getEnvironment());
                }
        }

}
