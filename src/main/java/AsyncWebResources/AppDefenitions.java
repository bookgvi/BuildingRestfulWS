package AsyncWebResources;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.container.AsyncResponse;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@ApplicationScoped
class AppDefenitions {
  BlockingQueue<AsyncResponse> responses = new LinkedBlockingQueue<>();

}
