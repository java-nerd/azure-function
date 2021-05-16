package com.aj.function;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.CosmosDBInput;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class HttpTriggerFunction {
	/**
	 * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
	 * 1. curl -d "HTTP Body" {your host}/api/HttpExample
	 * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
	 */
	@FunctionName("HttpExampleWithCosmosDBInput")
	public HttpResponseMessage run(
		@HttpTrigger(
			name = "req",
			methods = {HttpMethod.GET, HttpMethod.POST},
			authLevel = AuthorizationLevel.ANONYMOUS)
			HttpRequestMessage<Optional<String>> request,
		final ExecutionContext context,
		@CosmosDBInput(
			name = "input", databaseName = "dummy", collectionName = "dummy",
			id = "{Query.id}",
			partitionKey = "{Query.partitionKeyValue}",
			connectionStringSetting = "AzureCosmosDBConnection") Optional<String> item) {
		context.getLogger().info("Java HTTP trigger processed a request.");

		if (!item.isPresent()) {
			return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
				       .body("No Document Present!").build();
		} else {
			return request.createResponseBuilder(HttpStatus.OK)
				       .body("Hello: " + item.get()).build();
		}
	}

}
