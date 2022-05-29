package ahd.ulib.visualization.animatedmodels;

import ahd.ulib.jmath.datatypes.functions.Mapper2D;
import ahd.ulib.utils.Utils;
import ahd.ulib.visualization.canvas.CoordinatedCanvas;
import ahd.ulib.visualization.canvas.Render;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Mapper2DVisualization implements Render {
    private Mapper2D mapper;
    private CoordinatedCanvas cc;

    public Mapper2DVisualization(CoordinatedCanvas cc, Mapper2D mapper) {
        this.mapper = mapper;
        this.cc = cc;
    }

    public CoordinatedCanvas getCc() {
        return cc;
    }

    public void setCc(CoordinatedCanvas cc) {
        this.cc = cc;
    }

    public Mapper2D getMapper() {
        return mapper;
    }

    public void setMapper(Mapper2D mapper) {
        this.mapper = mapper;
    }

    @Override
    public void render(@NotNull Graphics2D g2d) {
        if (mapper == null)
            return;
        final var w = cc.getWidth();
        final var h = cc.getHeight();
        g2d.drawImage(Utils.createImage(w, h, i -> {
            var cn = mapper.map(cc.coordinateX(i % w), cc.coordinateY(i / w));
            return new Color(
                    (float) Math.abs(cn.x % 1),
                    (float) Math.abs(cn.y % 1),
                    (float) Math.abs(cn.distanceFromOrigin() % 1)
            ).getRGB();
        }, 40), 0, 0, null);
    }

    @Override
    public void tick() {

    }
}
