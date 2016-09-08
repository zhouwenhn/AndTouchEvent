# AndTouchEvent
android事件分发机制
1.Android View事件组成
(1)事件响应
Android事件分为点击，长按，拖动，滑动等，状态主要是由ACTION_DOWN(按下), ACTION_MOVE(移动), ACTION_UP(抬起)组成。事件必须是由ACTION_DOWN触发，才能有后面一系列的事件响应。
(2)View与ViewGroup有哪些方法响应事件
查看源码可以知道,View与ViewGroup主要几个事件组成
View:
public boolean dispatchTouchEvent(MotionEvent event),
public boolean onTouchEvent(MotionEvent event)
ViewGroup
public boolean dispatchTouchEvent(MotionEvent event)
public boolean onTouchEvent(MotionEvent event)
public boolean onInterceptTouchEvent(MotionEvent ev)
可以看出ViewGroup与View的方法事件不同，ViewGroup多了onInterceptTouchEvent(MotionEvent ev)一个拦截事件。因为LinerLayout,RelativeLayout等容器继承ViewGroup,可能是因为容器里有各种View,所以ViewGroup可以控制拦截事件。
下面说下这几个事件的作用
dispatchTouchEvent(MotionEvent event)：负责android view事件分发。
onInterceptTouchEvent(MotionEvent ev)：负责事件拦截，ViewGroup才有这个事件。
onTouchEvent(MotionEvent event):触摸事件
android 的视图都继承View,例如TextView, 所有容器继承ViewGroup,例如RelativeLayout
2.Android事件传递经过哪几个过程
首先看下ViewGroup源码,View的事件onTouchEvent（2079行），及ViewGroup的事件onInterceptTouchEvent（2108行）是由ViewGroup的dispatchTouchEvent触发的。
 
(1)Activity、View的事件传递过程
layout xml
 
首先自定义View（MyButton）代码如下
 
在Activity里实现这几个事件方法：
 
点击按钮事件打印如下：
 
由此可以看出事件是从Activity中传递到View中。经历了一个ACTION_DOWN，多个ACTION_MOVE和一个ACTION_UP事件。事件先从activity的dispatchTouchEvent，再到View的dispatchTouchEvent，接着是一系列Activity和View的ACTION_MOVE移动事件。最后到Activity,View的ACTION_UP
(2)Activity、ViewGroup、View的嵌套事件传递过程
自定义layout
 
 
点击按钮事件打印如下：
  
如图点击事件可已看出
当View在嵌套view中事件传递是从Activity>ViewGroup>View,过程如Activity、View的事件传递过程。
先down再move最后up的过程。
3.Android事件拦截
Activity的事件分发：
 
当@return boolean Return true if this event was consumed.>当事件被消耗掉就返回true.
下面实验下：
1.当把MyRelativeLayout里的onInterceptTouchEvent返回值改成true,则事件如下：
 
点击日志如下：
 
由此可见：从Activity的dispatchEventTouch到MyRelativeLayout的dispatchTouchEvent>onInterceptTouchEvent>onTouchEvent完全自己消耗了，如果这几个方法当返回true表示消耗，这时就不会往下传递。
2.当把MyRelativeLayout里的dispatchTouchEvent返回值改成true,则事件如下：
 
点击日志如下：
 
根据传递顺序，因为dispatchTouchEvent返回true,所以不会传递到onInterceptTouchEvent，效果同上。
3.当把MyButton里的dispatchTouchEvent返回值改成true
 
点击日志如下：
 
同上，根据传递顺序不会往下传递。
4.总结
（1）事件传递过程是从PhoneWindow>Activity>ViewGroup>View
（2）当非嵌套View的情况下，事件从dispatchTouchEvent>onTouch>onTouchEvent>onClick;当是嵌套View 的情况下，事件是：dispatchTouchEvent>onInterceptTouchEvent(ViewGroup才有)>onTouch>onTouchEvent>onClick。
其中经过dispatchTouchEvent, onTouchEvent, onInterceptTouchEvent,View有前两个事件，ViewGroup拥有onInterceptTouchEvent，主要负责View的事件的分发，事件的处理，事件的拦截。
（3）Return true if this event was consumed。
测试demo见附件


