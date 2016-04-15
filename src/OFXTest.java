import net.sf.ofx4j.client.*;
import net.sf.ofx4j.client.impl.BaseFinancialInstitutionData;
import net.sf.ofx4j.client.impl.FinancialInstitutionServiceImpl;
import net.sf.ofx4j.client.impl.LocalResourceFIDataStore;

import java.util.List;

/**
 * Created by phongpham on 1/17/16.
 */
public class OFXTest {

    public static void main(String[] args){
        try{
            FinancialInstitutionDataStore store = new LocalResourceFIDataStore();
            FinancialInstitutionService fiService = new FinancialInstitutionServiceImpl();
            for(FinancialInstitutionData fid : store.getInstitutionDataList()){
                System.out.println(fid.getId() + ":::" + fid.getName() + ":::" + fid.getOrganization());
                FinancialInstitution fi = fiService.getFinancialInstitution(fid);
                try{
                    FinancialInstitutionProfile profile = fi.readProfile();
                    System.out.println(profile.getFinancialInstitutionName());
                    System.out.println(profile.getAddress1() + ":::" + profile.getAddress2() + ":::" + profile.getAddress3());
                    System.out.println(profile.getCity() + ", " + profile.getState() + " " + profile.getZip());
                    System.out.println(profile.getCountry());
                    System.out.println(profile.getCustomerServicePhone() + ":::" + profile.getTechnicalSupportPhone());
                    System.out.println(profile.getSiteURL());

                }catch (Exception ex){
                    System.out.println("Fail to read profile with message: " + ex.getMessage());
                }
                System.out.println("**************************");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
