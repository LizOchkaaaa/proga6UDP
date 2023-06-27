package org.example.server;

import org.example.Request;

public class UniqueId {
    private static Integer id;

    public UniqueId() {
        id = 0;
    }

    public Request getNewId(Request request) {
        if (request.getStudyGroup()!=null) {
            request.getStudyGroup().setId(++id);
        }
            return request;

    }

    public static int getCurrentID() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
