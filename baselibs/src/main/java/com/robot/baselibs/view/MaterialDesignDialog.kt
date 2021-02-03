package com.robot.baselibs.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.DialogFragment
import com.blankj.utilcode.util.StringUtils
import com.robot.baselibs.R
import com.robot.baselibs.util.WebViewUtil
import java.io.Serializable


/**
 * <pre>
 *     e-mail :18721411287@163.com
 *     time   : 2018/7/18
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:
 * </pre>
 * @author niejunfeng

 */

class MaterialDesignDialog : DialogFragment {

    private lateinit var builder: Builder


    @SuppressLint("ValidFragment")
    constructor(builder: Builder) : super() {
        this.builder = builder
    }

    constructor() : super()

    private var model: Model = Model.NORMAL_DIALOG

    private lateinit var rootView: View

    private lateinit var progressbar: LightingProgressbar


    private lateinit var contentView: WebView

    private lateinit var confirmView: TextView

    private lateinit var cancelView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.dialog_material_design, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val window = dialog!!.window
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val lp = window.attributes
        lp.gravity = Gravity.CENTER //底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = lp
        window.setGravity(Gravity.CENTER)
        initView()
        return rootView
    }

    override fun onStart() {
        super.onStart()
        //设置 dialog 的宽高
        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        //设置 dialog 的背景为 null
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }


    private fun initView() {
        val positiveText = builder.positiveText
        val negativeText = builder.negativeText
        val title = builder.mTitle
        val content = builder.content

        confirmView = rootView.findViewById<TextView>(R.id.tv_confirm)
        cancelView = rootView.findViewById<AppCompatImageView>(R.id.iv_guanbi)
        contentView = rootView.findViewById<WebView>(R.id.content)
        progressbar = rootView.findViewById<LightingProgressbar>(R.id.progress)

        isCancelable = builder.isCancelable

        if (!StringUtils.isEmpty(positiveText)) {
            confirmView.text = positiveText
            confirmView.setOnClickListener { builder.onPositive(this) }
            confirmView.visibility = View.VISIBLE
        }

        if (!StringUtils.isEmpty(negativeText)) {
            cancelView.setOnClickListener { builder.onNegative() }
            cancelView.visibility = View.VISIBLE
        } else {
            cancelView.visibility = View.INVISIBLE
        }


        if (!StringUtils.isEmpty(content)) {
//            contentView.text = content
            WebViewUtil.loadData(contentView, content)
            contentView.visibility = View.VISIBLE
        }

    }

    fun setDialogModel(model: Model) {
        when (model) {
            Model.NORMAL_DIALOG -> {
                progressbar.visibility = View.GONE
                contentView.visibility = View.VISIBLE
                confirmView.visibility = View.VISIBLE
                cancelView.visibility = View.VISIBLE
            }
            Model.PROGRESS_PROGRESS -> {
                contentView.visibility = View.GONE
                progressbar.visibility = View.VISIBLE
                confirmView.visibility = View.GONE
                cancelView.visibility = View.GONE
            }
        }
    }

    fun setProgress(progress: Float) {
        if (progressbar.visibility != View.VISIBLE) progressbar.visibility = View.VISIBLE
        progressbar.progress = progress
    }


    fun setContent(content: String) {
        if (contentView.visibility != View.VISIBLE) contentView.visibility = View.VISIBLE
        WebViewUtil.loadData(contentView, content)
    }

    fun hidePositiveText() {
        confirmView.visibility = View.GONE
    }

    fun hideNegativeText() {
        cancelView.visibility = View.GONE
    }

    class Builder(var context: Context) : Serializable {
        /**
         * 标题
         */
        internal var isCancelable: Boolean = true
        /**
         * 标题
         */
        internal var mTitle: String = ""
        /**
         * 标题
         */
        internal var progress: Float = 0f
        /**
         * 提示内容
         */
        internal var content: String = ""

        /**
         * 确定按钮
         */
        internal var positiveText: String = ""

        /**
         * 取消按钮
         */
        internal var negativeText: String = ""

        /**
         *点击确定的回调
         */
        internal var onPositive: (dialog: MaterialDesignDialog) -> Unit = {}

        /**
         * 点击取消的回调
         */
        internal var onNegative: () -> Unit = {}

        fun withTitle(title: String): Builder {
            mTitle = title
            return this
        }

        fun withContent(content: String): Builder {
            this.content = content
            return this
        }

        fun withProgress(progress: Float): Builder {
            this.progress = progress
            return this
        }

        fun setIsCancelable(isCancelable: Boolean): Builder {
            this.isCancelable = isCancelable
            return this
        }

        fun withPositiveText(positiveText: String): Builder {
            this.positiveText = positiveText
            return this
        }

        fun withNegativeText(negativeText: String): Builder {
            this.negativeText = negativeText
            return this
        }

        fun onPositive(onPositive: (dialog: MaterialDesignDialog) -> Unit): Builder {
            this.onPositive = onPositive
            return this
        }

        fun onNegative(onNegative: () -> Unit): Builder {
            this.onNegative = onNegative
            return this
        }

        fun builder(): MaterialDesignDialog = MaterialDesignDialog(this)
    }

    enum class Model {
        PROGRESS_PROGRESS,
        NORMAL_DIALOG
    }
}