package com.goodow.android.performance.dummy;

import com.goodow.android.performance.cases.AdvancedListActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

  /**
   * An array of sample (dummy) items.
   */
  public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

  /**
   * A map of sample (dummy) items, by ID.
   */
  public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

  static {
    // Add some sample items.
    addItem(new DummyItem("1", "List (Advanced)", AdvancedListActivity.class));
  }

  private static void addItem(DummyItem item) {
    ITEMS.add(item);
    ITEM_MAP.put(item.id, item);
  }

  /**
   * A dummy item representing a piece of content.
   */
  public static class DummyItem {
    public final String id;
    public final String content;
    public final Class targetActivity;

    public DummyItem(String id, String content, Class targetActivity) {
      this.id = id;
      this.content = content;
      this.targetActivity = targetActivity;
    }

    @Override
    public String toString() {
      return content;
    }
  }
}
