package krati.store.index;

import java.io.IOException;
import java.util.Map.Entry;

import krati.io.Closeable;
import krati.util.IndexedIterator;

/**
 * Index.
 * 
 * @author jwu
 * 
 * <p>
 * 06/04, 2011 - Added interface Closeable 
 */
public interface Index extends Iterable<Entry<byte[], byte[]>>, Closeable {
    
    public int capacity();
    
    public byte[] lookup(byte[] keyBytes);
    
    public void update(byte[] keyBytes, byte[] metaBytes) throws Exception;
    
    public IndexedIterator<byte[]> keyIterator();
    
    @Override
    public IndexedIterator<Entry<byte[], byte[]>> iterator();
    
    public void persist() throws IOException;
    
    public void sync() throws IOException;
    
    public void clear() throws IOException;
}
