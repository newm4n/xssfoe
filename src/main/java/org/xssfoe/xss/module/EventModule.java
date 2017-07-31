package org.xssfoe.xss.module;

import java.util.regex.Pattern;

/**
 * Created by fneman on 6/22/17.
 */
public class EventModule extends AbstractXSSStripperModule {
    private Pattern[] pattern = new Pattern[] {
            Pattern.compile("FSCommand", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("seekSegmentTime", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("on(Abort|Activate|AfterPrint|AfterUpdate|BeforeActivate|BeforeCopy|BeforeCut|BeforeDeactivate|BeforeEditFocus|BeforePaste|BeforePrint|BeforeUnload|BeforeUpdate|Begin|Blur|Bounce|CellChange|Change|Click|CtextMenu|CtrolSelect|Copy|Cut|DataAvailable|DataSetChanged|DataSetComplete|DblClick|Deactivate|Drag|DragEnd|DragLeave|DragEnter|DragOver|DragDrop|DragStart|Drop|End|Error|ErrorUpdate|FilterChange|Finish|Focus|FocusIn|FocusOut|HashChange|Help|Input|KeyDown|KeyPress|KeyUp|LayoutComplete|Load|LoseCapture|MediaComplete|MediaError|Message|MouseDown|MouseEnter|MouseLeave|MouseMove|MouseOut|MouseOver|MouseUp|MouseWheel|Move|MoveEnd|MoveStart|Offline|Online|OutOfSync|Paste|Pause|PopState|Progress|PropertyChange|ReadyStateChange|Redo|Repeat|Reset|Resize|ResizeEnd|ResizeStart|Resume|Reverse|RowsEnter|RowExit|RowDelete|RowInserted|Scroll|Seek|Select|SelectiChange|SelectStart|Start|Stop|Storage|SyncRestored|Submit|TimeError|TrackChange|Undo|Unload|URLFlip)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL)
    };

    @Override
    public Pattern[] getPatterns() {
        return pattern;
    }

    @Override
    public String about() {
        return "event fragment";
    }

}
