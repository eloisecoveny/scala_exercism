import Bearing.{Bearing, East, North, South, West}

case class Robot(bearing: Bearing, coordinates: (Int,Int)) {

  def turnRight: Robot = {
    bearing match {
      case North => Robot(Bearing.East, this.coordinates)
      case East => Robot(Bearing.South, this.coordinates)
      case South => Robot(Bearing.West, this.coordinates)
      case West => Robot(Bearing.North, this.coordinates)
    }
  }

  def turnLeft: Robot = {
    bearing match {
      case North => Robot(Bearing.West, this.coordinates)
      case East => Robot(Bearing.North, this.coordinates)
      case South => Robot(Bearing.East, this.coordinates)
      case West => Robot(Bearing.South, this.coordinates)
    }
  }

  def advance: Robot = {
    val newCoordinates = bearing match {
      case North => (this.coordinates._1, this.coordinates._2 + 1)
      case East => (this.coordinates._1 + 1, this.coordinates._2)
      case South => (this.coordinates._1, this.coordinates._2 - 1)
      case West => (this.coordinates._1 - 1, this.coordinates._2)
    }
    Robot(this.bearing, newCoordinates)
  }

  def simulate(input: String): Robot = {
    val instructions = input.split("")
    instructions.foreach(instruction => {
      instruction match {
        case ("R") => this.turnRight
        case ("L") => this.turnLeft
        case ("A") => this.advance
      }
    })
    this
  }
}

object Bearing extends Enumeration {
  type Bearing = Value
  val North, East, South, West = Value
}
