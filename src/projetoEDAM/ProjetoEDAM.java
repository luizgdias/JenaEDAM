/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoEDAM;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.management.Query;
import org.apache.http.util.TextUtils;
import org.apache.jena.atlas.lib.ProgressMonitor;
import static org.apache.jena.enhanced.BuiltinPersonalities.model;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import static org.apache.jena.query.ResultSetFormatter.out;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.util.FileManager;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.NsIterator;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.RSIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import static org.apache.jena.riot.system.StreamRDFLib.writer;
import static org.apache.jena.sparql.engine.http.Service.base;
import org.apache.jena.util.PrintUtil;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.OWL2;
import org.apache.jena.vocabulary.RDFS;
import static org.apache.jena.vocabulary.VOID.NS;
import static org.apache.jena.vocabulary.VOID.properties;
import sun.invoke.empty.Empty;

/**
 *
 * @author luizgustavo
 */
public class ProjetoEDAM {

    public static void main(String[] args) throws IOException {
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF);
        String ontology = "file:/home/luizgustavo/NetBeansProjects/ProjetoEdam/src/projetoEDAM/EDAM.owl";
        FileWriter out = new FileWriter( "ontologyRdfTest.owl" );
        model.read(ontology, "RDF/XML");

        
        showOntologyComponents(model, "operation_0004");
        //queryingDataflows(model, subject, predicate, object, out);
        //queryingEquivalentTransformations(model);
    }

    private static void showOntologyComponents(OntModel model, String termo) {
                String queryString
                = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "
                + "PREFIX edam: <http://edamontology.org/>  "
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>"
                + "PREFIX xml:<http://www.w3.org/XML/1998/namespace>"
                + "select ?classes ?name "
                + "where { ?classes rdfs:subClassOf <http://edamontology.org/operation_0004> . ?classes rdfs:label ?name .} LIMIT 200"
                + "\n ";

        org.apache.jena.query.Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        // Output query results   
        //showing off the query results about all Data_transformations
        System.out.println("EDAM Operations:");
        ResultSetFormatter.out(System.out, results, query);
    }

}
