package no.hvl.dat250.rest.todos;

import com.google.gson.JsonElement;

public class StandardResponse {

        private StatusResponse status;
        private String message;
        private JsonElement data;

        public StandardResponse(StatusResponse status) {
            this.status = status;
        }
        public StandardResponse(StatusResponse status, String message) {
            this.status = status;
            this.message = message;
        }
        public StandardResponse(StatusResponse status, JsonElement data) {
            this.status = status;
            this.data = data;
        }

        // getters and setters

}
