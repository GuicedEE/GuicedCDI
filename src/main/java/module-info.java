import com.google.inject.gee.BindScopeProvider;
import com.google.inject.gee.InjectionPointProvider;
import com.guicedee.cdi.GuiceCDIModule;
import com.guicedee.cdi.GuiceCDIProviderImpl;
import com.guicedee.cdi.ICDIProvider;
import com.guicedee.cdi.implementations.*;
import com.guicedee.client.services.lifecycle.IGuiceModule;

module com.guicedee.cdi {
    exports com.guicedee.cdi;
    requires transitive com.guicedee.client;
    requires jakarta.cdi;
    requires jakarta.el;

    provides ICDIProvider with GuiceCDIProviderImpl;
    provides IGuiceModule with GuiceCDIModule;
    provides BindScopeProvider with BindScopeProvision;
    provides InjectionPointProvider with InjectionPointProvision;
    provides com.google.inject.gee.BindingAnnotationProvider with BindingAnnotationsProvision;
    provides com.google.inject.gee.NamedAnnotationProvider with NamedAnnotationProvision;
    provides com.google.inject.gee.InjectorAnnotationsProvider with InjectorAnnotationsProvision;


    uses ICDIProvider;
}
