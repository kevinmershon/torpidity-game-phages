app = {
  screen: null

  init: ->
    container = $("#screenContainer")[0]
    this.screen = Raphael(container, 0,0, 400,300)
    background = this.screen.rect(0,0, 400,300)
    background.attr("fill", "#000000")

  drawCircle: ->
    circle = this.screen.circle(50, 40, 10)
    circle.attr({
      fill: "#ff0000"
      stroke: "#ffffff"
    })
    circle.click(
      (event) ->
        alert "sup"
    )
}

$(document).ready ->
  app.init()
  app.drawCircle()