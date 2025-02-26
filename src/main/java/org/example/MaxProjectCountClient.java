package org.example;

public class MaxProjectCountClient {
    private String name;
    private Long projectCount;

    public MaxProjectCountClient(String name, Long projectCount) {
        this.name = name;
        this.projectCount = projectCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Long projectCount) {
        this.projectCount = projectCount;
    }
}
