package com.ambevtech.app.util;

import com.ambevtech.app.exception.EnumErrorException;
import com.ambevtech.app.exception.ServiceException;
import com.github.dozermapper.core.Mapper;
import org.apache.commons.beanutils.ConvertUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//@Component
public class MappingUtil {

//    @Autowired
//    private ModelMapper mapper;
//
//    private static ModelMapper staticMapper;
//
//    @Autowired
//    private Mapper mapperDozer;
//
//    private static Mapper staticMapperDozer;
//
//    @PostConstruct
//    public void init() {
//        MappingUtil.staticMapperDozer = mapperDozer;
//        MappingUtil.staticMapper = mapper;
//    }
//
//    public static <D> D map(final Object source, final Class<D> target) {
//        try {
//            return staticMapper.map(source, target);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ServiceException(EnumErrorException.FALHA_EXTRAIR_DADOS_OBJETO);
//        }
//    }
//
//    public static void map(Object source, Object destination) {
//        try {
//            staticMapper.map(source, destination);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ServiceException(EnumErrorException.FALHA_EXTRAIR_DADOS_OBJETO);
//        }
//    }
//
//    public static <D> D mapDozer(final Object source, final Class<D> target) {
//        try {
//            return staticMapperDozer.map(source, target);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ServiceException(EnumErrorException.FALHA_EXTRAIR_DADOS_OBJETO);
//        }
//    }
//
//    public static void mapDozer(Object source, Object destination) {
//        try {
//            staticMapperDozer.map(source, destination);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ServiceException(EnumErrorException.FALHA_EXTRAIR_DADOS_OBJETO);
//        }
//    }
//
//    public static <T, U> List<U> mapListDozer(final List<T> source, final Class<U> destType) {
//        final List<U> dest = new ArrayList<>();
//        try {
//            for (T element : source) {
//                dest.add(staticMapperDozer.map(element, destType));
//            }
//            return dest;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ServiceException(EnumErrorException.FALHA_EXTRAIR_DADOS_OBJETO);
//        }
//    }
//
//    public static <T, U> List<U> mapList(final List<T> source, final Class<U> destType) {
//        final List<U> dest = new ArrayList<>();
//        try {
//            for (T element : source) {
//                dest.add(staticMapper.map(element, destType));
//            }
//            return dest;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ServiceException(EnumErrorException.FALHA_EXTRAIR_DADOS_OBJETO);
//        }
//    }
//
//    public static <T, U> List<U> mapListObjectDozer(final List<?> source, final Class<U> destType) {
//        try {
//            return source.stream().map(obj -> MappingUtil.map((Object[]) obj, destType)).collect(Collectors.toList());
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ServiceException(EnumErrorException.FALHA_EXTRAIR_DADOS_OBJETO);
//        }
//    }
//
//    public static <D> D map(final Object[] source, final Class<D> target) {
//        try {
//            Integer index = -1;
//            Class<?> clazz = Class.forName(target.getName());
//            Object newInstance = clazz.getDeclaredConstructor().newInstance();
//            for (Field field : clazz.getDeclaredFields()) {
//                field.setAccessible(true);
//                if (!field.getType().isPrimitive()) {
//                    ++index;
//                    if (source != null && source[index] != null) {
//                        field.set(newInstance, ConvertUtils.convert(source[index], field.getType()));
//                    }
//                }
//            }
//            return staticMapper.map(newInstance, target);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ServiceException(EnumErrorException.FALHA_EXTRAIR_DADOS_OBJETO);
//        }
//    }
//
//    public static <T, U> List<U> map(final Collection<T> source, final Class<U> destType) {
//
//        final List<U> dest = new ArrayList<>();
//        try {
//            for (T element : source) {
//                dest.add(staticMapper.map(element, destType));
//            }
//
//            return dest;
//        } catch (Exception e) {
//            throw new ServiceException(EnumErrorException.FALHA_EXTRAIR_DADOS_OBJETO);
//        }
//    }

}
