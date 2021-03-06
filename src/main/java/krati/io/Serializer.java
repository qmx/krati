package krati.io;

/**
 * An interface for object serialization.
 * 
 * @author jwu
 * 06/29, 2011
 * 
 * @param <T> An object to serialize.
 */
public interface Serializer<T> {
    
    /**
     * Serialize an object into a byte array.
     * 
     * @param object - an object to be serialized by this Serializer.
     * @return a byte array which is the raw representation of an object.
     */
    public byte[] serialize(T object) throws SerializationException;
    
    /**
     * Deserialize an object from its raw bytes generated by {{@link #serialize(Object)}.
     * 
     * @param bytes - the raw bytes from which an object is constructed.
     * @return an object constructed from the raw bytes.
     * @throws SerializationException if the object cannot be constructed from the raw bytes.
     */
    public T deserialize(byte[] bytes) throws SerializationException;
}
