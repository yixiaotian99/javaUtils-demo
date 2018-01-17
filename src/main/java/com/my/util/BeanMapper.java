package com.my.util;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 用于 vo 互转 do
 */
public class BeanMapper {

    private static DozerBeanMapper dozer = new DozerBeanMapper();

    static {
        List<String> files = new ArrayList<String>();
        files.add("spring-dozer-mapping.xml");
        dozer.setMappingFiles(files);
    }


    /**
     * 构造新的destinationClass实例对象，通过source对象中的字段内容
     * 映射到destinationClass实例对象中，并返回新的destinationClass实例对象。
     *
     * @param source           源数据对象
     * @param destinationClass 要构造新的实例对象Class
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }


    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List destinationList = new ArrayList();
        for (Iterator i = sourceList.iterator(); i.hasNext(); ) {
            Object sourceObject = i.next();
            Object destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }


    /**
     * 将对象source的所有属性值拷贝到对象destination中.
     *
     * @param source      对象source
     * @param destination 对象destination
     */
    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }
}
