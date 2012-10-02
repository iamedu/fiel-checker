import mx.gob.sat.sgi.SgiCripto.{SgiCertificado, SgiLlavePrivada, SgiFirma}

import java.io.File
import java.io.FileInputStream
import scala.io.Source

import org.bouncycastle.jce.provider.BouncyCastleProvider

import java.security.Security

def testCert(name: String) = {
    val cert = new File("certs/cert" + name)
    val key  = new File("certs/key" + name)
    val pass = Source.fromFile("certs/pass" + name, "ISO8859-15").mkString.replaceAll("\\n", "")

    
    val certificado = new SgiCertificado();
    val llavePrivada = new SgiLlavePrivada();
    val firma = new SgiFirma();
 
    try {
        llavePrivada.inicia(new FileInputStream(key), pass.getBytes)
        println("GOOD PASSWORD")
    } catch {
        case _ => println("BAD PASSWORD")
    }

}

val names = for(f <- new File("certs").listFiles;
                if(f.getName.startsWith("cert"))) yield f.getName.replaceAll("cert", "")

Security.addProvider(new BouncyCastleProvider())
names.foreach(testCert)



