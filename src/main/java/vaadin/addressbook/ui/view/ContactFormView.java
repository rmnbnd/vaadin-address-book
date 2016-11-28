package vaadin.addressbook.ui.view;

import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = ContactFormView.VIEW_NAME)
public class ContactFormView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "contact";

    public ContactFormView() {
        TextField name = new TextField("Name");
        TextField email = new TextField("Email");
        TextField phone = new TextField("Phone");
        Button cancel = new Button("Cancel");
        Button save = new Button("Save", FontAwesome.SAVE);
        CssLayout actions = new CssLayout(save, cancel);

        // Aff components
        addComponents(name, email, phone, actions);

        // Configure and style components
        setSpacing(true);
        setMargin(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
