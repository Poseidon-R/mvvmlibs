package com.robot.baselibs.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.blankj.utilcode.util.StringUtils
import com.flyco.dialog.widget.base.BaseDialog
import com.robot.baselibs.R
import java.io.Serializable

/**
 * <pre>
 *     e-mail : junfeng.nie@duntech.com.cn
 *     time   : 2020-08-14
 *     desc   :
 *     version: org.clc.app.widget.dialog
 *     Copyright: Copyright（c）2018
 *     Company:
 * </pre>
 * @author niejunfeng
 */
class ContentDialog : BaseDialog<ContentDialog> {

    private lateinit var builder: Builder


    @SuppressLint("ValidFragment")
    constructor(builder: Builder, context: Context) : super(context) {
        this.builder = builder
    }

    constructor(context: Context) : super(context)


    override fun setUiBeforShow() {

    }

    override fun onCreateView(): View {
        widthScale(0.9f)
        val inflate = View.inflate(context, R.layout.dialog_content, null)
        initView(inflate)
        return inflate
    }

    private fun initView(view: View) {
        val tvContent = view.findViewById<AppCompatTextView>(R.id.tv_content)

        val confirmView = view.findViewById<AppCompatTextView>(R.id.tv_confirm)
        if (!StringUtils.isEmpty(builder.content)) {
            tvContent.text = builder.content
            tvContent.visibility = View.VISIBLE
        }
        if (!StringUtils.isEmpty(builder.positiveText)) {
            confirmView.text = builder.positiveText
        }

        confirmView.setOnClickListener {
            builder.onPositive(this)
        }


    }

    class Builder(var context: Context) : Serializable {

        /**
         * 金钱
         */
        internal var content: String = ""

        /**
         * 确定按钮
         */
        internal var positiveText: String = ""

        /**
         * 取消按钮
         *
         */
        internal var cancelText: String = ""

        /**
         *点击确定的回调
         */
        internal var onPositive: (dialog: ContentDialog) -> Unit = {}


        internal var itemClick: (position: Int) -> Unit = {

        }

        fun withContentText(context: String): Builder {
            this.content = context

            return this
        }

        fun withPositiveText(positiveText: String): Builder {
            this.positiveText = positiveText
            return this
        }


        fun onPositive(onPositive: (dialog: ContentDialog) -> Unit): Builder {
            this.onPositive = onPositive
            return this
        }

        fun onItemClick(itemClick: (position: Int) -> Unit): Builder {

            this.itemClick = itemClick
            return this
        }


        fun builder(): ContentDialog = ContentDialog(this, context)
    }
}
