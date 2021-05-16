package com.aj.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.BlobTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;

import java.time.LocalDateTime;

/**
 * Azure Functions with BLOB Trigger.
 */
public class BlobTriggerExample {

	@FunctionName("BlobTriggerExample")
	public void blobMonitor(
		@BlobTrigger(name = "file",
			dataType = "binary",
			//container name and filer on file name, {name}.PNG will trigger this only if PNG files are
			//added or updated.
			path = "mycontainer/{name}",
			connection = "AzureWebJobsStorage") byte[] content,
		@BindingName("name") String filename,
		final ExecutionContext context
	) {
		context.getLogger().info("Blob :" + filename + " is added! at " + LocalDateTime.now());
	}
}
