package $package

import skalch.AngelicSketch
import sketch.dyn.BackendOptions
import sketch.util.cli
import sketch.util.DebugOut._ // assertFalse, not_implemented, print()

class MyAppSketch() extends AngelicSketch {
    val second_value = MyAppCliOptions("second_value")

    val tests = Array( () )
    def main() {
        synthAssertTerminal(??(List("a", "b", "c")) == "c")
        synthAssertTerminal(??(100) == 63)
    }
}

object MyAppCliOptions extends cli.CliOptionGroup {
    var result : cli.CliOptionResult = null
    import java.lang.Integer
    add("--second_value", 63 : Integer, "value which the second hole should match")
    def apply(x : String) : Int = result.long_(x).intValue
}

object MyAppMain {
    def main(args: Array[String])  = {
        val cmdopts = new cli.CliParser(args)
        BackendOptions.add_opts(cmdopts)
        MyAppCliOptions.result = MyAppCliOptions.parse(cmdopts)
        skalch.AngelicSketchSynthesize(() => new MyAppSketch())
    }
}
