package beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

/**
 * {@link BeanInfo} 示例
 *
 * @Author:JQK
 * @Date:2021/3/5 15:58
 **/

public class BeanInfoDemo {
    public static void main(String[] args) throws IntrospectionException {
        //bean的自省会有一个异常
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(propertyDescriptor -> {
                    //System.out.println(propertyDescriptor);
                    //propertyDescriptor允许添加属性编辑器 -PropertyEditor
                    //GUI: text（String）->propertyType
                    //name->String   age->Integer
                    Class<?> propertyType = propertyDescriptor.getPropertyType();
                    String typeName = propertyType.getName();
                    if ("age".equals(typeName)) {
                        //为"age"字段/增加PropertyEditor,需要关联
                        propertyDescriptor.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
                        // propertyDescriptor.createPropertyEditor();
                    }
                });
    }

    static class StringToIntegerPropertyEditor extends PropertyEditorSupport {
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            setValue(value);
        }
    }

}
