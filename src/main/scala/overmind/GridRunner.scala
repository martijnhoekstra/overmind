/*
This file is part of Overmind.

Overmind is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Overmind is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Overmind.  If not, see <http://www.gnu.org/licenses/>.
*/

package overmind

import scala.sys.process._

object GridRunner {
  def run(job : Jobtype, name : String, args : String*) {
    val runner = job match {
      case Daemon => "jstart"
      case Oneoff => "jsub"
    }
    println("trying to run:")
    println(s"$name with args $args")
    (runner :: "-cwd" :: name :: args.toList).!
  }
}

object LocalRunner {
    def run(job : Jobtype, name : String, args : String*) {
    val runner = job match {
      case Daemon => "jstart"
      case Oneoff => "jsub"
    }
    println("trying to run:")
    println(s"$name with args $args")
    (name :: args.toList).!
  }
}

sealed abstract class Jobtype
case object Oneoff extends Jobtype
case object Daemon extends Jobtype