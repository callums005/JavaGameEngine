package renderEngine;

import models.RawModel;
import models.TexturedModel;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Renderer
{
    public void prepare() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GL11.glClearColor(0.0f, 0.0f, 0.5f, 0.0f);
    }

    public void renderRawModel(RawModel model) {
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);

        GL30.glBindVertexArray(0);
    }

    public void render(TexturedModel texturedModel) {
        GL30.glBindVertexArray(texturedModel.getRawModel().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1); // FOR IMAGE SHADER ONLY
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, texturedModel.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(0); // FOR IMAGE SHADER ONLY
        GL30.glBindVertexArray(0);
    }
}
