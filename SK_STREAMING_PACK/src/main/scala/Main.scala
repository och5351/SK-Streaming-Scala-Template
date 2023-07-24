import org.siltron.ioc.SparkController
import org.siltron.di.model.ConfigModel

object Main {
  def main(args: Array[String]): Unit = {
    val sparkController = new SparkController

    sparkController.sparkStreamingStarter(ConfigModel.readConfig().get("app").get("name").toString)

  }
}