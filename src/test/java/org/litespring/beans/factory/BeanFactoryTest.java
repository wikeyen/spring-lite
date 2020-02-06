package org.litespring.beans.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.service.v1.PetStoreService;

import static org.junit.jupiter.api.Assertions.*;

class BeanFactoryTest {

    private DefaultBeanFactory beanFactory;
    private XmlBeanDefinitionReader xmlBeanDefinitionReader;

    @BeforeEach
    void setUp() {
        beanFactory = new DefaultBeanFactory();
        xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
    }

    @Test
    void should_get_bean_of_PetStoreService() {
        xmlBeanDefinitionReader.loadBeanDefinitions("petstore-v1.xml");

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStore");
        assertEquals("org.litespring.service.v1.PetStoreService", beanDefinition.getBeanClassName());

        PetStoreService petStore = (PetStoreService) beanFactory.getBean("petStore");
        assertNotNull(petStore);
    }

    @Test
    void should_throw_BeanCreationException_when_getBean_given_an_invalid_bean_name() {
        xmlBeanDefinitionReader.loadBeanDefinitions("petstore-v1.xml");

        assertThrows(BeanCreationException.class, () -> beanFactory.getBean("invalidBean"));
    }

    @Test
    void should_throw_BeanDefinitionStoreException_when_getBeanDefinition_given_an_invalid_xml_file_name() {
        assertThrows(BeanDefinitionStoreException.class, () -> xmlBeanDefinitionReader.loadBeanDefinitions("xxx.xml"));
    }

}