package com.activemq_apache_camel.schema_validator;

import com.activemq_apache_camel.model.ArticleDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.InputStream;

@Component
public class SchemaValidator {


    public void validateArticleAgainstSchema(ArticleDTO articleDTO)
    {
        try(InputStream inputStream = getClass().getResourceAsStream("/article.json") ) {


            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
            Schema schema = SchemaLoader.load(rawSchema);
            ObjectMapper om = new ObjectMapper();
            String json = om.writerWithDefaultPrettyPrinter().writeValueAsString(articleDTO);
            System.out.print(json);
            schema.validate(new JSONObject(json));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
