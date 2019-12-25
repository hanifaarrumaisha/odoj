//
// Created by hanif on 12/25/2019.
//

#include <jni.h>
#include <iostream>

extern "C" JNIEXPORT jint JNICALL
Java_id_ac_ui_cs_mobileprogramming_hanifa_odoj_MainActivity_additionJNI(JNIEnv* env,
jobject /* this */, jint left, jint right){
    jint result;
    result = left + right;
    return result;
}
