package rest;

public class Acronym {

    private String name;


    private String description;
    private String[] links;
    private String[] labels;

    public Acronym(String name, String description, String[] links, String[] labels) {
        this.name = name;
        this.description = description;
        this.links = links;
        this.labels = labels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getLinks() {
        return links;
    }

    public void setLinks(String[] links) {
        this.links = links;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "{ \"name\":\"" + getName() + "\", description:\"" + getDescription() + "\", links:" + parseArrayToString(getLinks()) + ", labels:" + parseArrayToString(getLabels()) + "}";
    }

    private String parseArrayToString(String[] array) {
        String parsedArray = "[";
        for(int i = 0; i<array.length; i++) {
            parsedArray += "\"" + array[i] + "\"";
            if(i!=array.length - 1) {
                parsedArray += ",";
            }
        }

        parsedArray += "]";
        return parsedArray;
    }
}


