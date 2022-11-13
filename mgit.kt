import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.io.File
import java.security.MessageDigest

class MGit() {
    fun CMD__init(){
        if(!Files.isDirectory(Paths.get(".mgit"))){
            // Create info, objects, refs (With heads and tags) folders and HEAD and config files
            Files.createDirectories(Paths.get(".mgit", "objects"))
            Files.createDirectories(Paths.get(".mgit", "info"))
            Files.createDirectories(Paths.get(".mgit", "refs", "heads"))
            Files.createDirectories(Paths.get(".mgit", "refs", "tags"))
            Files.createFile(Paths.get(".mgit", "HEAD"));
            Files.createFile(Paths.get(".mgit", "config"))
        }
        println("Initialized mgit Successfully.")
    }

    fun CMD__hash_object(path : String){
        try{
            val fileContent = String(Files.readAllBytes(Paths.get(path)))
            val hash = MessageDigest.getInstance("SHA-1").digest(fileContent.toByteArray()).joinToString(""){"%02x".format(it)}
            Files.copy(Paths.get(path), Paths.get(".mgit", "objects", hash) , StandardCopyOption.REPLACE_EXISTING)
            println(hash)
        }
        catch(e : Exception){
            println(e)
        }
    }

    fun CMD__cat_file(object_hash : String){
        try{
            val content : String = File(Paths.get(".mgit","objects",object_hash).toString()).readText()
            println(content)
        }
        catch(e : Exception){
            println(e)
        }
    }    
}


fun main(args: Array<String>) {
    if(args.isEmpty()){
        print("Please add some command line arguments")
        return
    }
    val MG = MGit()
    val command = args[0]

    when(command){
        "init" -> MG.CMD__init()
        "hash-object" -> {
            val path = args[1]
            MG.CMD__hash_object(path)
        }
        "cat-file" -> {
            val object_hash = args[1]
            MG.CMD__cat_file(object_hash)
        }
    }
}

