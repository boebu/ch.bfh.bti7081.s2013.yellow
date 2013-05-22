package ch.bfh.bti7081.s2013.yellow.ui.medication;

import ch.bfh.bti7081.s2013.yellow.model.medication.Medicament;
import ch.bfh.bti7081.s2013.yellow.model.medication.Prescription;
import ch.bfh.bti7081.s2013.yellow.model.person.Patient;
import ch.bfh.bti7081.s2013.yellow.service.medication.MedicamentService;
import ch.bfh.bti7081.s2013.yellow.service.medication.PrescriptionService;
import ch.bfh.bti7081.s2013.yellow.service.person.PatientService;
import ch.bfh.bti7081.s2013.yellow.util.SpringHelper;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

@Theme("book-examples")
public class MyPopupUI extends UI {
    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("All prescriptions");
        
        // Have some content for it
        VerticalLayout content = new VerticalLayout();
        Label label =
            new Label("Here you'll see all prescriptions");
        label.setSizeUndefined();
        content.addComponent(label);
        content.setComponentAlignment(label,
            Alignment.MIDDLE_CENTER);
        content.setSizeFull();
        setContent(content);
    }
}