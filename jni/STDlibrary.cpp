#include <jni.h>
#include <string.h>
#include<android/log.h>
#include "stdio.h"

#define LOG_TAG "LX"
#define d(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define i(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

// C/C++定义的方法必须为Java+包名+activity名+你的函数名，每个部分之间要用下划线_来连接，
// 而且参数也是固定的。所以定义C/C++函数的固定格式为:
// Java_PackageName_ActivityName_function(JNIEnv* env, jobject obj) {...}
//
// 这里extern "C"是为了防止C++编译二进制链接库时对函数进行改名，其实加不加都不影响这段实现代码的运行效果，
// 但是如果你的程序因为java.lang.UnsatisfiedLinkError: Native method not found这
// 错误而崩溃的话你还是加上吧。

extern "C" jint Java_com_example_ndktest_MainActivity_hello(JNIEnv * env,
		jobject thiz, jint a) {
	jint b;
	b = a + 12 + 12;
	return b;
}
jobject getPackageManagerObject(JNIEnv *env, jobject context_object) {
	//Get class of Context Object
	jclass context_class = env->GetObjectClass(context_object);
	jmethodID methodId = env->GetMethodID(context_class, "getPackageManager",
			"()Landroid/content/pm/PackageManager;");
	jobject package_manager = env->CallObjectMethod(context_object, methodId);
	return package_manager;
}

jstring getPackageName(JNIEnv *env, jobject context_object) {
	jclass context_class = env->GetObjectClass(context_object);
	jmethodID method_id = env->GetMethodID(context_class, "getPackageName",
			"()Ljava/lang/String;");
	return (jstring) env->CallObjectMethod(context_object, method_id);
}

jobject getAllPackageInfo(JNIEnv* env, jobject package_manager_object) {
	//Get all installed packages Information, make a java call getInstalledPackages(...)
	jclass pack_manager_class = env->GetObjectClass(package_manager_object);
	jclass pack_manager_static_class = env->FindClass(
			"android/content/pm/PackageManager");

	jfieldID field_signatures = env->GetStaticFieldID(
			pack_manager_static_class, "GET_SIGNATURES", "I");
	int get_signatures_value = env->GetStaticIntField(
			pack_manager_static_class, field_signatures);
	jmethodID methodId = NULL;
	methodId = env->GetMethodID(pack_manager_class, "getInstalledPackages",
			"(I)Ljava/util/List;");
	jobject list_package_object = env->CallObjectMethod(
			package_manager_object, methodId, get_signatures_value);
	return list_package_object;
}

jobject getIterator(JNIEnv* env, jobject list_package_object) {
	//make a java call to get List.Iterator()
	jclass list_class = env->GetObjectClass(list_package_object);
	jmethodID methodId = env->GetMethodID(list_class, "iterator",
			"()Ljava/util/Iterator;");
	jobject iterator = env->CallObjectMethod(list_package_object, methodId);
	return iterator;
}

extern "C" jbyteArray Java_com_example_ndktest_MainActivity_getSign(JNIEnv *env,
		jclass jcl, jobject context_object, jstring str_package_name) {

	i("Call getSign()");
	jobject package_manager_object = NULL;
	package_manager_object = getPackageManagerObject(env, context_object);
	if (package_manager_object != NULL) {
		i("Get PackageManager Successfully!");
	}
	else {
		i("Get PackageManager Failed!");
		return NULL;
	}

	//Get all installed packages Information, make a java call getInstalledPackages(...)
	jobject list_package_object = NULL;
	list_package_object = getAllPackageInfo(env, package_manager_object);
	if (list_package_object != NULL) {
		i("Get All Package Info Successfully!");
	}
	else {
		("Get Package Info Failed!");
		return NULL;
	}

	jobject iterator = getIterator(env, list_package_object);
	if (iterator != NULL) {
		i("Get iterator Successfully!");
	}
	else {
		i("Get iterator Failed!");
		return NULL;
	}

	//get the signature from the package
	jclass iterator_class = env->GetObjectClass(iterator);
	jmethodID methodId_for_hasNext = env->GetMethodID(iterator_class,
			"hasNext", "()Z");
	jmethodID methodId_for_next = env->GetMethodID(iterator_class, "next",
			"()Ljava/lang/Object;");

	while (env->CallBooleanMethod(iterator, methodId_for_hasNext)) {
		jobject package_info = env->CallObjectMethod(iterator,
				methodId_for_next);
		jclass package_info_class = env->GetObjectClass(package_info);
		jfieldID fieldId_packageNname = env->GetFieldID(package_info_class,
				"packageName", "Ljava/lang/String;");
		jstring package_name = (jstring) env->GetObjectField(package_info,
				fieldId_packageNname);
		const char * c_package_name = env->GetStringUTFChars(package_name,
				NULL);
		const char * c_str_package_name = env->GetStringUTFChars(
				str_package_name, NULL);
		i("Start comparing ");
		//Two Package names is the same
		if (!strcmp(c_package_name, c_str_package_name)) {
			i("Same Package Name Found!!!");
			jfieldID field_package_signatures = env->GetFieldID(
					package_info_class, "signatures",
					"[Landroid/content/pm/Signature;");
			if (field_package_signatures != NULL) {
				i("Get field_package_signatures Successfully!");
			}
			else {
				i("Get field_package_signatures Failed!");
				return NULL;
			}

			jobjectArray signature_array = NULL;
			signature_array = (jobjectArray) env->GetObjectField(
					package_info, field_package_signatures);

			if (signature_array != NULL) {
				i("Get signature_array Successfully!");
			}
			else {
				i("Get signature_array Failed!");
				return NULL;
			}
			jobject signature_object = env->GetObjectArrayElement(
					signature_array, 0);
			jclass signature_class = env->GetObjectClass(
					signature_object);
			jmethodID methodId_for_toByteArray = env->GetMethodID(
					signature_class, "toByteArray", "()[B");
			jbyteArray signature_info = NULL;
			signature_info = (jbyteArray) env->CallObjectMethod(
					signature_object, methodId_for_toByteArray);

			if (signature_info != NULL) {
				i("Get signature_info byte array Successfully!");
			}
			else {
				i("Get signature_info byte array Failed!");
				return NULL;
			}
			return signature_info;
		}

		env->DeleteLocalRef(package_info);
		env->DeleteLocalRef(package_info_class);
	}
	return NULL;
}

extern "C" jstring Java_com_example_ndktest_MainActivity_getPublicKey(
		JNIEnv *env, jclass thiz, jbyteArray sig_bytes) {

	jclass cert_factory_class = env->FindClass(
			"java/security/cert/CertificateFactory");
	jmethodID methodId_getInstance =
			env->GetStaticMethodID(cert_factory_class, "getInstance",
					"(Ljava/lang/String;)Ljava/security/cert/CertificateFactory;");
	jstring encode = env->NewStringUTF("X.509");
	jobject cert_factory_object = env->CallStaticObjectMethod(
			cert_factory_class, methodId_getInstance);
	jmethodID methodId_generateCertificate = env->GetMethodID(
			cert_factory_class, "generateCertificate",
			"(Ljava/io/InputStream;)Ljava/security/cert/Certificate;");

	//Get ByteArrayInputStream Object

	jclass byteArrayInputStream_class = env->FindClass(
			"java/io/ByteArrayInputStream");
	jmethodID methodId_constructor = env->GetMethodID(
			byteArrayInputStream_class, "<init>", "([B)V");
	jobject byteArrayInputStream_object = env->NewObject(
			byteArrayInputStream_class, methodId_constructor, sig_bytes);

	//Get X509Certificate Object
	jobject x509Certificate_object = env->CallObjectMethod(
			cert_factory_object, methodId_generateCertificate,
			byteArrayInputStream_object);

	//Get PublicKey
	jclass x509Certificate_class = env->GetObjectClass(
			x509Certificate_object);
	jmethodID methodId_getPublicKey = env->GetMethodID(x509Certificate_class,
			"getPublicKey", "()Ljava/security/PublicKey;");
	jobject publicKey_object = env->CallObjectMethod(x509Certificate_object,
			methodId_getPublicKey);
	jclass publicKey_class = env->GetObjectClass(publicKey_object);

	jmethodID methodId_toString = env->GetMethodID(publicKey_class,
			"toString", "()Ljava/lang/String;");
	jstring str_publicKey = (jstring) env->CallObjectMethod(publicKey_object,
			methodId_toString);
	return str_publicKey;
}

jstring getInnerApkKey(JNIEnv *env) {
	//drugkey签名对应的publicKey
	const char * apkKey =
			"OpenSSLRSAPublicKey{modulus=b63d9bbdfbbd91af4a8e492886f901c1f0335138b0a0d9f0841ee8491588846cbbebbc8b1f60d4cc5a1fd221a5601ba026741139641ec0ae66cb05ab6537a23032333c4c02ce63076edc2d00bbfe7fdbf1f14152b408f3931f4b4a72fd61a06d205a3dcfb8b4886959eb432ffe2154bdeacd23d1b586a5b705f007608a59e6544af165a231275c15c227b8ce28b507023e4400186641d36a7e94655d71bffdd97433e595db0e11bd7f76d747419df99b4f1b1973bbc52e75ff538e690725353b41a0e07d0df8f876432925db46a0976f65c4284f39b34f16fdfb4a7fd91da9635a6742909939d4d519a9d80e6cff02c35515faab298835b31d0bf7b785e05355,publicExponent=10001}";
	return env->NewStringUTF(apkKey);
}

jstring getPrivateKey(JNIEnv *env) {
	//签名私钥
	const char* privateKey =
			"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALys+oYaxqv4FYju8C1poM6qmHLjWPnXzqEJT0NxyFXgdaK/Qe9DcpcASod9mIAdlLIxJEyYNlWeonAJVYW8pQ+pTVGwI9n0iaT71ldWQzcMN3Dvi/+zpgw3HxxO7HJtEIlR84pvILv1yceCZCqqQ4O/4SemsG00oTiTyD3SM2ZvAgMBAAECgYBLToeX6ywNC7Icu7Hljll+45yBjri+0CJLKFoYw1uA21xYnxoEE9my54zX04uA502oafDhGYfmWLDhIvidrpP6oaluURb/gbV5Bdcm98gGGVgm6lpK+G5N/eawXDjP0ZjxXb114Y/Hn/oVFVM9OqcujFSV+Wg4JgJ4Mmtdr35gYQJBAPbhx030xPcep8/dL5QQMc7ddoOrfxXewKcpDmZJi2ey381X+DhuphQ5gSVBbbunRiDCEcuXFY+R7xrgnP+viWcCQQDDpN8DfqRRl+cUhc0z/TbnSPJkMT/IQoFeFOE7wMBcDIBoQePEDsr56mtc/trIUh/L6evP9bkjLzWJs/kb/i25AkEAtoOf1k/4NUEiipdYjzuRtv8emKT2ZPKytmGx1YjVWKpyrdo1FXMnsJf6k9JVD3/QZnNSuJJPTD506AfZyWS6TQJANdeF2Hxd1GatnaRFGO2y0mvs6U30c7R5zd6JLdyaE7sNC6Q2fppjmeu9qFYq975CKegykYTacqhnX4I8KEwHYQJAby60iHMAYfSUpu//f5LMMRFK2sVif9aqlYbepJcAzJ6zbiSG5E+0xg/MjEj/Blg9rNsqDG4RECGJG2nPR72O8g==";
	return env->NewStringUTF(privateKey);
}

jbyteArray getSign(JNIEnv *env, jclass thiz, jobject context) {
	jobject package_manager_object = getPackageManagerObject(env, context);
	if (package_manager_object == NULL) {
		return NULL;
	}

	jclass pack_manager_class = env->GetObjectClass(package_manager_object);
	jmethodID packageinfo_id = env->GetMethodID(pack_manager_class,
			"getPackageInfo",
			"(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;");
	jfieldID field_signatures = env->GetStaticFieldID(pack_manager_class,
			"GET_SIGNATURES", "I");
	jclass pack_manager_static_class = env->FindClass(
			"android/content/pm/PackageManager");
	jint get_signatures_value = env->GetStaticIntField(
			pack_manager_static_class, field_signatures);
	jstring packageName = getPackageName(env, context);
	jobject package_info_obj = env->CallObjectMethod(package_manager_object,
			packageinfo_id, packageName, get_signatures_value);
	jclass package_info_class = env->GetObjectClass(package_info_obj);
	jfieldID signs_filed_id = env->GetFieldID(package_info_class,
			"signatures", "[Landroid/content/pm/Signature;");
	jobjectArray signs_array = (jobjectArray) env->GetObjectField(
			package_info_obj, signs_filed_id);
	jobject sign = env->GetObjectArrayElement(signs_array, 0);
	jclass sign_class = env->GetObjectClass(sign);
	jmethodID methodId_for_toByteArray = env->GetMethodID(sign_class,
			"toByteArray", "()[B");
	jbyteArray signature_array = (jbyteArray) env->CallObjectMethod(sign,
			methodId_for_toByteArray);

	if (signature_array == NULL) {
		return NULL;
	}
	return signature_array;
	env->DeleteLocalRef(package_info_obj);
	env->DeleteLocalRef(package_info_class);
	env->DeleteLocalRef(package_manager_object);
	env->DeleteLocalRef(signs_array);
	env->DeleteLocalRef(sign);
	env->DeleteLocalRef(signature_array);
}

//验证程序是否被重新打包;1：未破解（如果签名已改变则为被重新打包）
extern "C" jint Java_com_example_ndktest_MainActivity_isCopyOfRight(JNIEnv *env,
		jclass thiz, jobject context) {
	jstring apkKey_j = getInnerApkKey(env);
	jstring package = getPackageName(env, context);
	jstring publicKey = Java_com_example_ndktest_MainActivity_getPublicKey(
			env, thiz, getSign(env, thiz, context));

	return 1; //测试
//	return !strcmp(env->GetStringUTFChars(apkKey_j, 0),
//			env->GetStringUTFChars(publicKey, 0));
}

extern "C" jstring Java_com_example_ndktest_MainActivity_getPrivateKey(
		JNIEnv *env, jclass thiz, jobject context) {
	if (Java_com_example_ndktest_MainActivity_isCopyOfRight(env, thiz,
			context)) {
		//如果未被破解则返回私钥
		return getPrivateKey(env);
	}
	else {
		return env->NewStringUTF("");
	}
}

jbyteArray getByteArray(JNIEnv*env, jstring paintext) {
	jclass clsstring = env->FindClass("java/lang/String");
	jmethodID mid = env->GetMethodID(clsstring, "getBytes",
			"(Ljava/lang/String;)[B");
	jbyteArray bytearray = (jbyteArray) env->CallObjectMethod(paintext, mid,
			env->NewStringUTF("utf-8"));
	return bytearray;
}

jstring byte2hex(JNIEnv*env, jbyteArray byteArray) {
	jsize size = env->GetArrayLength(byteArray);
	char* buf = new char[size * 3 + 1];
	sprintf(buf, "\0");
	jbyte* byte = (jbyte*) env->GetByteArrayElements(byteArray, 0);
	for (int i = 0; i < size; ++i) {
		char c = byte[i];
		sprintf(buf, "%s%02X", buf, c);
		if (i < size - 1) {
			sprintf(buf, "%s:", buf);
		}
	}
	d("%s", buf);
	return env->NewStringUTF(buf);
	delete[] buf;
	delete[] byte;
}

//MD5加密
extern "C" jstring Java_com_example_ndktest_MainActivity_MD5(JNIEnv *env,
		jclass thiz, jstring paintext) {
	jclass md5_class = env->FindClass("java/security/MessageDigest");
	if (md5_class == NULL)
		d("FindClass == null");
	jmethodID method_id = env->GetStaticMethodID(md5_class, "getInstance",
			"(Ljava/lang/String;)Ljava/security/MessageDigest;");
	if (method_id == NULL)
		d("getInstance = null");
	jobject md5_obj = env->CallStaticObjectMethod(md5_class, method_id,
			env->NewStringUTF("MD5"));

	if (md5_obj == NULL)
		d("md5_obj == null");
	jmethodID messageDigest_class = env->GetMethodID(md5_class, "update",
			"([B)V");
	env->CallVoidMethod(md5_obj, messageDigest_class,
			getByteArray(env, paintext));
	jmethodID digest_id = env->GetMethodID(md5_class, "digest", "()[B");
	jbyteArray bytes = (jbyteArray) env->CallObjectMethod(md5_obj, digest_id);
	return byte2hex(env, bytes);

	env->DeleteLocalRef(md5_obj);
	env->DeleteLocalRef(bytes);
}

void test(JNIEnv *env) {
	int tt = 9;
	char a[] = "";
	char b[] = "b";
	for (int i = 0; i < 10; i++) {
		strcat(a, b);
	}

}
