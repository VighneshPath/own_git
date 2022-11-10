import java.nio.file.Files
import java.nio.file.Paths
import java.io.File

class MGit(commands: Array<String>) {
    val cmd1 = commands[0]
    init {
        if(cmd1.equals("init")){
            if(!Files.isDirectory(Paths.get(".mgit"))){
                // Create info, objects, refs (With heads and tags) folders and HEAD and config files
                Files.createDirectories(Paths.get(".mgit", "objects"))
                Files.createDirectories(Paths.get(".mgit", "info"))
                Files.createDirectories(Paths.get(".mgit", "refs", "heads"))
                Files.createDirectories(Paths.get(".mgit", "refs", "tags"))

                var file = File(".mgit\\HEAD")
                file.createNewFile()

                var file2 = File(".mgit\\config")
                file2.createNewFile()

            }
            println("Initialized mgit Successfully.")

        }
    }
    
}


fun main(args: Array<String>) {
    if(args.isEmpty()){
        print("Please add some command line arguments")
        return
    }
    var mgit_obj = MGit(args)      
}

