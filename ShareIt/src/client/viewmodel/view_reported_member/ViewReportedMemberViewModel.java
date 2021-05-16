package client.viewmodel.view_reported_member;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewReportedMemberViewModel {
    private final StringProperty searchField;
    private final StringProperty reportedNameLabel;
    private final StringProperty reporterNameLabel;
    private final StringProperty commentaryLabel;

    public ViewReportedMemberViewModel(){
        searchField = new SimpleStringProperty("Search");
        reportedNameLabel = new SimpleStringProperty();
        reporterNameLabel = new SimpleStringProperty();
        commentaryLabel = new SimpleStringProperty();
    }

    public StringProperty getSearchField(){
        return searchField;
    }

    public StringProperty getReportedNameLabel(){
        return reportedNameLabel;
    }

    public StringProperty getReporterNameLabel(){
        return reporterNameLabel;
    }

    public StringProperty getCommentaryLabel(){
        return commentaryLabel;
    }
}