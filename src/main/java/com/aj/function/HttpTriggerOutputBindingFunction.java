package com.aj.function;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class HttpTriggerOutputBindingFunction {
	/**
	 * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
	 * 1. curl -d "HTTP Body" {your host}/api/HttpExample
	 * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
	 */
	@FunctionName("HttpExampleWithCosmosDBInput")
	public HttpResponseMessage run(
		@HttpTrigger(
			name = "req",
			methods = HttpMethod.POST,
			authLevel = AuthorizationLevel.ANONYMOUS)
			HttpRequestMessage<Optional<String>> request,
		final ExecutionContext context,
	//	@QueueOutput()
		@CosmosDBOutput(
			name = "output", databaseName = "dummy", collectionName = "dummy",
			partitionKey = "{Query.partitionKeyValue}",
			createIfNotExists = true,
			connectionStringSetting = "AzureCosmosDBConnection") OutputBinding<String> outputBinding) {
		context.getLogger().info("Java HTTP trigger processed a request.");

		if (!outputBinding.isPresent()) {
			return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
				       .body("No Document Present!").build();
		} else {
			return request.createResponseBuilder(HttpStatus.OK)
				       .body("Hello: " + item.get()).build();
		}
	}

}
