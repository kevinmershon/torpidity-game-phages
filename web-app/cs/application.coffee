app = {
  screen: null

  init: ->
    container = $("#screenContainer")[0]
    this.screen = Raphael(container, 0,0, 400,300)
    background = this.screen.rect(0,0, 400,300)
    background.attr("fill", "#000000")

  drawCircle: (x, y, radius) ->
    circle = this.screen.circle(x, y, radius)
    circle.attr({
      "fill": "#b4cdcd"
      "stroke": "#000000"
      "stroke-width": 1.5
    })
    circle.mouseover( (event) ->
      circle.data("glow", circle.glow({
        width: 10,
        color: "#ffffff"
      }))
    )
    circle.mouseout( (event) ->
      glow = circle.data("glow")
      glow.remove()
      circle.data("glow", null)
    )
    circle.click(
      (event) ->
        circle.stop().animate({
          cx: 200
          cy: 150
          r: 25
        }, 350, "<>")
    )
}

$(document).ready ->
  app.init()
  app.drawCircle(50, 40, 10)