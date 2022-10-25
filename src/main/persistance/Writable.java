package persistance;

import org.json.JSONObject;

public interface Writable {
    JSONObject toJson();
}
