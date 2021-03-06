package krati.store;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Map.Entry;

import krati.array.DataArray;
import krati.util.IndexedIterator;

/**
 * DataStoreIterator
 * 
 * @author  jwu
 * @since   0.3.5
 * @version 0.4.2
 * 
 * <p>
 * 08/10, 2011 - Implemented IndexedIterator <br/>
 */
final class DataStoreIterator implements IndexedIterator<Entry<byte[], byte[]>> {
    private final ArrayList<Entry<byte[], byte[]>> _bucket;
    private final DataStoreHandler _dataHandler;
    private final DataArray _dataArray;
    private int _index = 0;
    
    DataStoreIterator(DataArray dataArray, DataStoreHandler dataHandler) {
        this._dataArray = dataArray;
        this._dataHandler = dataHandler;
        this._bucket = new ArrayList<Entry<byte[], byte[]>>(20);
        this.findNext();
    }
    
    @Override
    public boolean hasNext() {
        if(_bucket.size() == 0) {
            findNext();
        }
        return _bucket.size() > 0;
    }
    
    @Override
    public Entry<byte[], byte[]> next() {
        int size = _bucket.size();
        if (size == 0) {
            findNext();
            size = _bucket.size();
        }
        
        if(size > 0) {
            return _bucket.remove(--size);
        }
        
        throw new NoSuchElementException();
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
    private void findNext() {
        while(_index < _dataArray.length()) {
            byte[] data = _dataArray.get(_index++);
            
            if(data != null) {
                List<Entry<byte[], byte[]>> entries = _dataHandler.extractEntries(data);
                if(entries != null && entries.size() > 0) {
                    _bucket.addAll(entries);
                    break;
                }
            }
        }
    }
    
    @Override
    public int index() {
        return _index;
    }
    
    @Override
    public void reset(int indexStart) {
        _index = Math.max(0, indexStart);
        _bucket.clear();
        findNext();
    }
}
