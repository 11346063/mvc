package tw.edu.ntub.imd.birc.firstmvc.service.transformer.impl;


import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import tw.edu.ntub.birc.common.util.JavaBeanUtils;
import tw.edu.ntub.imd.birc.firstmvc.bean.TeacherBean;
import tw.edu.ntub.imd.birc.firstmvc.databaseconfig.entity.Teacher;
import tw.edu.ntub.imd.birc.firstmvc.service.transformer.TeacherTransformer;

@Component
public class TeacherTransformerImpl implements TeacherTransformer {
    // 記得import spring的
    @NonNull
    @Override
    public Teacher transferToEntity(@NonNull TeacherBean teacherBean) {
        // 複製左邊的給右邊的，null不複製、名字要相同
        return JavaBeanUtils.copy(teacherBean, new Teacher());
    }

    @NonNull
    @Override
    public TeacherBean transferToBean(@NonNull Teacher teacher) {
        return JavaBeanUtils.copy(teacher, new TeacherBean());
    }
}
