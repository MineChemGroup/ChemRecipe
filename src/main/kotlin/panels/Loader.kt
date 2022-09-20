package panels

import kotlinx.coroutines.*
import misc.Inst
import org.apache.commons.io.FileUtils
import java.awt.Image
import java.awt.Toolkit
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import javax.imageio.ImageIO
import javax.swing.JPanel
import kotlin.math.abs


class Loader(val jPanel: JPanel = JPanel()) {

    lateinit var baseFolder : File
    lateinit var iconsFolder : File
    lateinit var elementsFolder : File
    lateinit var compoundsFolder : File
    lateinit var recipeFolder : File

    lateinit var img : File
    lateinit var db : File
    lateinit var ws : File

    suspend fun init() {
        baseFolder = File(System.getProperty("user.home") + "/Documents/ChemRecipe")
        baseFolder.mkdir()
        iconsFolder = File(baseFolder.path + "/icons")
        elementsFolder = File(baseFolder.path + "/elements")
        compoundsFolder = File(baseFolder.path + "/compounds")
        recipeFolder = File(baseFolder.path + "/recipes")
        iconsFolder.mkdir()
        elementsFolder.mkdir()
        compoundsFolder.mkdir()
        recipeFolder.mkdir()
    }

    suspend fun paste(){
        val icoUrl: URL? = javaClass.getResource("/ico.png")
        val ico = File(baseFolder.path + "/ico.png")
        if (!ico.exists())
            FileUtils.copyURLToFile(icoUrl, ico)
        val icon: Image = Toolkit.getDefaultToolkit().getImage(ico.path)
        Inst.jframe.iconImage = icon

        val imgUrl: URL? = javaClass.getResource("/icons-minecraft.png")
        img = File(baseFolder.path + "/icons-minecraft.png")
        if (!img.exists())
            FileUtils.copyURLToFile(imgUrl, img)

        val dbUrl: URL? = javaClass.getResource("/icons.txt")
        db = File(baseFolder.path + "/icons.txt")
        if (!db.exists())
            FileUtils.copyURLToFile(dbUrl, db)

        loadresource("mcfont.ttf")
        loadresource("3x3.png")
        loadresource("arrow.png")
        loadresource("oneslot.png")

        if (isEmpty(elementsFolder.toPath())){
            for (i in 1..118){
                FileUtils.copyURLToFile(javaClass.getResource("/elements/$i.png"), File(elementsFolder.path + "/$i.png"))
            }
        }

        if (isEmpty(compoundsFolder.toPath())){
            for (i in 1..121){
                FileUtils.copyURLToFile(javaClass.getResource("/compounds/$i.png"), File(compoundsFolder.path + "/$i.png"))
            }
        }
    }

    suspend fun load() {
        if (!isEmpty(iconsFolder.toPath()))
            return

        val bufferedImage = ImageIO.read(img)
        val reader = BufferedReader(FileReader(db))
        var line = reader.readLine()
        while (line != null) {
            val lines = line.split(":")
            val name = lines[0].uppercase().replace("-", "_")
            val x1 = abs(lines[1].split(" ")[0].toInt())
            val y1 = abs(lines[1].split(" ")[1].toInt())

            val subimage = bufferedImage.getSubimage(x1, y1, 32, 32)
            val outputfile = File(iconsFolder.path + "/" + name + ".png")
            ImageIO.write(subimage, "png", outputfile)
            outputfile.createNewFile()
            line = reader.readLine()
        }
        reader.close()
    }

    fun isEmpty(path: Path) : Boolean{
        if (path.toFile().isDirectory)
            if (path.toFile().listFiles().isEmpty())
                return true

        return false
    }

    fun getAssets(folder : File) : ArrayList<File>{
        val listOfFiles = folder.listFiles()
        val list = arrayListOf<File>()
        for (i in 0 until (listOfFiles?.size!!)) {
            list.add(listOfFiles[i])
        }
        return list
    }

    fun getNumerical(folder : File, i : Int) : ArrayList<File>{
        val list = arrayListOf<File>()
        for (num in 1..i){
            list.add(File(folder.path + "/$num.png"))
        }
        return list
    }

    fun loadresource(name : String){
        val resourceUrl: URL? = javaClass.getResource("/$name")
        val resource = File(baseFolder.path + "/$name")
        if (!resource.exists())
            FileUtils.copyURLToFile(resourceUrl, resource)
    }
}