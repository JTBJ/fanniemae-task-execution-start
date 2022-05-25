package basePackage_resolver;

import java.util.Arrays;
import java.util.List;

import com.amazonaws.services.ecs.model.AwsVpcConfiguration;
import com.amazonaws.services.ecs.model.ContainerOverride;
import com.amazonaws.services.ecs.model.ListTaskDefinitionFamiliesRequest;
import com.amazonaws.services.ecs.model.ListTaskDefinitionFamiliesResult;
import com.amazonaws.services.ecs.model.NetworkConfiguration;
import com.amazonaws.services.ecs.model.RunTaskRequest;
import com.amazonaws.services.ecs.model.RunTaskResult;
import com.amazonaws.services.ecs.model.TaskOverride;

public class MyResolver {

	private RunTaskResult client;

	public void someMethod() {
		
		ListTaskDefinitionFamiliesRequest request = new ListTaskDefinitionFamiliesRequest();
	    ListTaskDefinitionFamiliesResult response = client.listTaskDefinitionFamilies(request);
	    List<String> ls = response.getFamilies();
	        String cmd[] ={"arg1","arg2"};
	        List<String> command = Arrays.asList(cmd);
	        TaskOverride overrides = new TaskOverride();
	        ContainerOverride containerOverrides = new ContainerOverride();
	        containerOverrides.setCommand(command);
	        overrides.withContainerOverrides(containerOverrides);
	        String subnets[] ={"subnet-bbb","subnet-ccc"};
	        List<String> subnetList = Arrays.asList(subnets);
	        String securityGrp[] ={"sg-xxx"};
	        List<String> securityGrpList = Arrays.asList(securityGrp    );
	        AwsVpcConfiguration awsvpcConfiguration = new AwsVpcConfiguration();
	        awsvpcConfiguration.setSubnets(subnetList);
	        awsvpcConfiguration.setSecurityGroups(securityGrpList);
	        NetworkConfiguration networkConfiguration = new NetworkConfiguration();
	        networkConfiguration.setAwsvpcConfiguration(awsvpcConfiguration);

	     // == modifying request variable because of duplicate declaration
	    RunTaskRequest request2 = new RunTaskRequest()
	            .withCluster("cluster-ecs")
	            .withTaskDefinition("task:1")
	            .withCount(1)
	            .withLaunchType("FARGATE")              
	            .withNetworkConfiguration(networkConfiguration)
	            .withOverrides(overrides);
	            
        // == modifying response variable because of duplicate declaration
	    RunTaskResult response2 = client.runTask(request2);  
	}
}
