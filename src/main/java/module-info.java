import com.guicedee.cdi.GuiceCDIModule;
import com.guicedee.cdi.GuiceCDIProviderImpl;
import com.guicedee.cdi.ICDIProvider;
import com.guicedee.guicedinjection.interfaces.IGuiceModule;

module com.guicedee.cdi {
    exports com.guicedee.cdi;
    requires transitive com.guicedee.client;
    requires jakarta.cdi;

    provides ICDIProvider with GuiceCDIProviderImpl;
    provides IGuiceModule with GuiceCDIModule;

    uses ICDIProvider;
}