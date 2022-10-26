package persistance;

import org.json.JSONObject;

// interface that models classes that need to be save-able
public interface Writable {
    JSONObject toJson();
}
