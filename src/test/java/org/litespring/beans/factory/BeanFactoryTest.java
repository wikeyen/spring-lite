package org.litespring.beans.factory;

import org.junit.jupiter.api.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.service.v1.PetStoreService;

import static org.junit.jupiter.api.Assertions.*;

class BeanFactoryTest {
    @Test
    void should_get_bean_of_PetStoreService() {
        BeanFactory beanFactory = new DefaultBeanFactory("petstore-v1.xml");
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStore");
        assertEquals("org.litespring.service.v1.PetStoreService", beanDefinition.getBeanClassName());

        PetStoreService petStore = (PetStoreService) beanFactory.getBean("petStore");
        assertNotNull(petStore);
    }

    @Test
    void should_throw_BeanCreationException_when_getBean_given_an_invalid_bean_name() {
        BeanFactory beanFactory = new DefaultBeanFactory("petstore-v1.xml");

        assertThrows(BeanCreationException.class, () -> beanFactory.getBean("invalidBean"));
    }

    @Test
    void should_throw_BeanDefinitionStoreException_when_getBeanDefinition_given_an_invalid_xml_file_name() {
        assertThrows(BeanDefinitionStoreException.class, () -> new DefaultBeanFactory("invalid.xml"));
    }

}