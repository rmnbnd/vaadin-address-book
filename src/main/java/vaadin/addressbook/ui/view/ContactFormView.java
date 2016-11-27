package vaadin.addressbook.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

@SpringView(name = ContactFormView.VIEW_NAME)
public class ContactFormView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "contact";

    @PostConstruct
    void init() {
        setMargin(true);
        setSpacing(true);
        addComponent(new Label("This is a contact form"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
