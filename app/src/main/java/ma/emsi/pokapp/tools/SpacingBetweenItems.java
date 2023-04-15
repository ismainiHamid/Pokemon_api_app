package ma.emsi.pokapp.tools;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpacingBetweenItems extends RecyclerView.ItemDecoration {
    private final int top;
    private final int left;
    private final int bottom;
    private final int right;

    public SpacingBetweenItems(int top, int left, int bottom, int right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.top = top;
        outRect.left = left;
        outRect.bottom = bottom;
        outRect.right = right;
    }
}
