package com.example.demo.widget

import android.content.Context
import android.support.annotation.IdRes
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import com.effective.R
import com.effective.android.panel.view.content.ContentContainerImpl
import com.effective.android.panel.view.content.IContentContainer
import com.effective.android.panel.view.content.IInputAction
import com.effective.android.panel.view.content.IResetAction

/**
 * 实现IContentContainer接口，可参考
 * [com.effective.android.panel.view.content.ContentFrameContainer] 等库提供的模版实现基础的container容器
 * demo已约束布局为例子，使用者按需扩展就可以了
 * Created by yummyLau on 2020/05/07
 * Email: yummyl.lau@gmail.com
 * blog: yummylau.com
 */
class ContentCusContainer @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr), IContentContainer {
    @IdRes
    var editTextId = 0

    @IdRes
    var resetViewId = 0
    var autoResetByOnTouch: Boolean = true
    private lateinit var contentContainer: ContentContainerImpl
    private fun initView(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ContentCusContainer, defStyleAttr, 0)
        editTextId = typedArray.getResourceId(R.styleable.ContentCusContainer_cus_edit_view, -1)
        resetViewId = typedArray.getResourceId(R.styleable.ContentCusContainer_cus_auto_reset_area, -1)
        autoResetByOnTouch = typedArray.getBoolean(R.styleable.ContentCusContainer_cus_auto_reset, autoResetByOnTouch)
        typedArray.recycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        contentContainer = ContentContainerImpl(this, autoResetByOnTouch, editTextId, resetViewId)
    }

    override fun layoutContainer(l: Int, t: Int, r: Int, b: Int) {
        contentContainer.layoutContainer(l, t, r, b)
    }

    override fun findTriggerView(id: Int): View? {
        return contentContainer.findTriggerView(id)
    }

    override fun changeContainerHeight(targetHeight: Int) {
        contentContainer.changeContainerHeight(targetHeight)
    }

    override fun getInputActionImpl(): IInputAction = contentContainer.getInputActionImpl()

    override fun getResetActionImpl(): IResetAction = contentContainer.getResetActionImpl()

    init {
        initView(attrs, defStyleAttr, 0)
    }
}