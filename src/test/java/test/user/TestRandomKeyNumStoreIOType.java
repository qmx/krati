package test.user;

import java.io.File;

import krati.core.StoreConfig;
import krati.core.StoreFactory;
import krati.store.DataStore;

/**
 * TestRandomKeyNumStoreIOType
 * 
 * @author jwu
 * 06/26, 2011
 */
public class TestRandomKeyNumStoreIOType extends TestRandomKeyNumStore {
    
    @Override
    protected DataStore<byte[], byte[]> createStore(File homeDir) throws Exception {
        StoreConfig config = new StoreConfig(homeDir, getCapacity());
        config.setSegmentFileSizeMB(getSegmentFileSizeMB());
        config.setSegmentFactory(createSegmentFactory());
        
        // Do not cache indexes in memory
        config.setIndexesCached(false);
        
        return StoreFactory.createDynamicDataStore(config);
    }
}
